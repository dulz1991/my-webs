<blockquote>�ҵ�������־�б�</blockquote>

<table class="striped">
        <thead>
          <tr>
              <th>No.</th>
              <th>����</th>
              <th>�����</th>
              <th>�������</th>
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
			        	<a href="/create?id=${item.id}" target="_blank">�༭</a>
			        </td>
			      </tr>
        	</#list>
        </tbody>
      </table>