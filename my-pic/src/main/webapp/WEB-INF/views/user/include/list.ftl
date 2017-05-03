<blockquote>我的旅行日志列表</blockquote>

<table class="striped">
        <thead>
          <tr>
              <th>No.</th>
              <th>标题</th>
              <th>被浏览</th>
              <th>最近更新</th>
              <th></th>
          </tr>
        </thead>

        <tbody>
        	<#list list as item>
        		<tr>
			        <td>${item_index+1}</td>
			        <td><a href="/list/detail?id=${item.id}" target="_blank">${item.title}</a></td>
			        <td>${item.title}</td>
			        <td>${item.updateTime?string("yyyy-MM-dd HH:mm:ss")}</td>
			        <td>
			        	<a href="/create?id=${item.id}" target="_blank">编辑</a>
			        </td>
			      </tr>
        	</#list>
        </tbody>
      </table>