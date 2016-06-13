package com.lyc.soa.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * 
 * dubbo ������
 * 
 * @date 2015��10��27�� ����10:50:20
 * @author maliang
 */
public class TestFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

		return invoker.invoke(invocation);
		// return null;
		// Ȩ��
		// return new RpcResult(new Throwable("û��Ȩ��"));
	}
}
