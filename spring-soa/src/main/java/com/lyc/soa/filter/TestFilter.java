package com.lyc.soa.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * 
 * dubbo 过滤器
 * 
 * @date 2015年10月27日 上午10:50:20
 * @author maliang
 */
public class TestFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

		return invoker.invoke(invocation);
		// return null;
		// 权限
		// return new RpcResult(new Throwable("没有权限"));
	}
}
