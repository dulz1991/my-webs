package com.demo.my.base.mybatis.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.demo.my.base.util.Page;

/**
 * 自动分页
 * 
 * @author 吴三�? 2015�?7�?8日下�?6:47:06
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PagePlugin implements Interceptor {
	
	public Object plugin(Object arg0) {
		// TODO Auto-generated method stub
		return Plugin.wrap(arg0, this);
	}
	
	public static final ThreadLocal<Page> localPage = new ThreadLocal<Page>();  
	
	 /** 
     * �?始分�? 
     * @param pageNum 
     * @param pageSize 
     */  
    public static void startPage(Page page) {  
        localPage.set(page);  
    }  
    
    /** 
     * 结束分页并返回结果，该方法必须被调用，否则localPage会一直保存下去，直到下一次startPage 
     * @return 
     */  
    public static Page getPage() {  
        Page page = localPage.get();  
        localPage.remove();  
        return page;  
    }  

	private String dialect = ""; // 数据库方�?
	private static String pageSqlId = ""; // mapper.xml中需要拦截的ID(正则匹配)

	public Object intercept(Invocation invocation) throws Throwable {
		if (localPage.get() == null) {  
			return invocation.proceed();
        }  
		
		if (invocation.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");
			// 拦截�?要分页的SQL
			/*if (mappedStatement.getId().matches(pageSqlId)) {
				
			}*/
			Page page = localPage.get();
			
			BoundSql boundSql = delegate.getBoundSql();
			Object parameterObject = boundSql.getParameterObject();// 分页SQL<select>中parameterType属�?�对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为�?
			
			Connection connection = (Connection) invocation.getArgs()[0];
			String sql = boundSql.getSql();
			
			if (parameterObject == null) {
				throw new NullPointerException("parameterObjectΪ��");
			} else {
				String countSql = "select count(0) from (" + sql + ")  tmp_count"; 
				PreparedStatement countStmt = connection.prepareStatement(countSql);
				BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
				setParameters(countStmt, mappedStatement, countBS, parameterObject);
				ResultSet rs = countStmt.executeQuery();
				int totalCount = 0;
				if (rs.next()) {
					totalCount = rs.getInt(1);
				}
				page.setTotalRecords(totalCount);  
	            int totalPage = totalCount / page.getPageSize() + ((totalCount % page.getPageSize() == 0) ? 0 : 1);  
	            page.setTotalPages(totalPage);
	            
				rs.close();
				countStmt.close();
			}
			
			String pageSql = buildPageSql(sql, page);
			ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql); // 将分页sql语句反射回BoundSql.
		}
		// 将执行权交给下一个拦截器  
		return invocation.proceed();
	}

	/**
	 * 对SQL参数(?)设�??,参�?�org.apache.ibatis.executor.parameter.
	 * DefaultParameterHandler
	 * 
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}

	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (Tools.isEmpty(dialect)) {
			try {
				throw new Exception("dialect property is not found!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*pageSqlId = p.getProperty("pageSqlId");
		if (Tools.isEmpty(pageSqlId)) {
			try {
				throw new Exception("pageSqlId property is not found!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
	
    /** 
     * 修改原SQL为分页SQL 
     * @param sql 
     * @param page 
     * @return 
     */  
    private String buildPageSql(String sql, Page page) {  
        StringBuilder pageSql = new StringBuilder(200);
        pageSql.append(sql);
		pageSql.append(" limit " + page.getStartRow() + "," + page.getPageSize());
		System.out.println("pageSql: " + pageSql.toString());
        return pageSql.toString();  
    }  

}
