<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<div class="list">
	
		<!-- the demo root element -->
		<ul id="side-menu" class="menu-root">
		  <item class="item" :model="treeData"></item>
		</ul>
	
    	<!-- <ul class="menu-root">
              <li>
                  <a href="/v2/guide/installation.html" class="sidebar-link">安装</a>
              </li>
              <li>
                  <a href="/v2/guide/index.html" class="sidebar-link current">介绍</a>
              <ul class="menu-sub"><li><a class="section-link" data-scroll="" href="#Vue-js-是什么">Vue.js 是什么</a></li><li><a class="section-link" data-scroll="" href="#起步">起步</a></li><li><a class="section-link active" data-scroll="" href="#声明式渲染">声明式渲染</a></li><li><a class="section-link" data-scroll="" href="#条件与循环">条件与循环</a></li><li><a class="section-link" data-scroll="" href="#处理用户输入">处理用户输入</a></li><li><a class="section-link" data-scroll="" href="#组件化应用构建">组件化应用构建</a></li><li><a class="section-link" data-scroll="" href="#与自定义元素的关系">与自定义元素的关系</a></li><li><a class="section-link" data-scroll="" href="#准备好了吗？">准备好了吗？</a></li></ul></li>
              <li>
                  <a href="/v2/guide/instance.html" class="sidebar-link">Vue 实例</a>
              </li>
              <li>
                  <a href="/v2/guide/syntax.html" class="sidebar-link">模板语法</a>
              </li>
              <li>
                  <a href="/v2/guide/computed.html" class="sidebar-link">计算属性</a>
              </li>
              <li>
                  <a href="/v2/guide/class-and-style.html" class="sidebar-link">Class 与 Style 绑定</a>
              </li>
              <li>
                  <a href="/v2/guide/conditional.html" class="sidebar-link">条件渲染</a>
              </li>
              <li>
                  <a href="/v2/guide/list.html" class="sidebar-link">列表渲染</a>
              </li>
              <li>
                  <a href="/v2/guide/events.html" class="sidebar-link">事件处理器</a>
              </li>
              <li>
                  <a href="/v2/guide/forms.html" class="sidebar-link">表单控件绑定</a>
              </li>
              <li>
                  <a href="/v2/guide/components.html" class="sidebar-link">组件</a>
              </li>
       	</ul> -->
	</div>

<script type="text/x-template" id="item-template">
<li>
	<div :class="{bold: isFolder}" @click="toggle">
		<a v-if="currentId==model.id" v-bind:href="model.url" v-bind:id="model.id" class="sidebar-link current">{{model.name}}</a>
		<a v-if="currentId!=model.id" v-bind:href="model.url" v-bind:id="model.id" class="sidebar-link">{{model.name}}</a>
      	<span v-if="isFolder">
			<i v-if="open" class="fa fa-chevron-up"></i>
			<i v-else class="fa fa-chevron-down"></i>
		</span>
    </div>
    <ul v-show="open" v-if="isFolder">
    	<item class="item" v-for="model in model.children" :model="model"></item>
     	<!-- <li class="add" @click="addChild">+</li> -->
    </ul>
</li>
</script>

<style>
.item {
  cursor: pointer;
}
.bold {
  font-weight: bold;
}
ul {
  padding-left: 1em;
  line-height: 1.5em;
  list-style-type: dot;
}
</style>
<script>

var data ={
  name: '${subMenu.name}列表',
  url:'javascript:;',
  children:${menuTreeData}
};
/* var data = {
  name: '${subMenu.name}列表',
  url:'/code/guide?id=1',
  children: [
    { name: 'hello' },
    { name: 'wat' },
    {
      name: 'child folder',
      children: [
        { name: 'hello' },
        { name: 'wat' }
      ]
    }
  ]
} */

// define the item component
Vue.component('item', {
  template: '#item-template',
  props: {
    model: Object
  },
  data: function () {
    return {
      open: true,
      currentId:"${code.id}"
    }
  },
  computed: {
    isFolder: function () {
      return this.model.children && this.model.children.length
    }
  },
  methods: {
    toggle: function () {
      if (this.isFolder) {
        this.open = !this.open
      }
    },
    changeType: function () {
      if (!this.isFolder) {
        Vue.set(this.model, 'children', []);
        this.addChild();
        this.open = true;
      }
    },
    addChild: function () {
      this.model.children.push({
        name: 'new stuff'
      })
    }
  }
})

// boot up the demo
var demo = new Vue({
  el: '#side-menu',
  data: {
    treeData: data
  }
})

/* $(function(){
	$("ul li.item a#${code.id}").addClass("current");
}); */
</script>
