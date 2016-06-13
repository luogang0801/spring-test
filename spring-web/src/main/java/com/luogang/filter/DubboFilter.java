package com.luogang.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

public class DubboFilter implements Filter {

	/**
	 * ��ȡ��½�û�����dubbo������
	 * 
	 * @see com.alibaba.dubbo.rpc.Filter#invoke(com.alibaba.dubbo.rpc.Invoker, com.alibaba.dubbo.rpc.Invocation)
	 */
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
/*
		Map<String, String> attachments = new HashMap<String, String>();
		// ��ȡ��ǰ��½�û�
		ShiroUser user = UserUtils.getLoginUser();
		if (user != null) {
			attachments.put(Constant.Global.USER_ID_FOR_DUBBO, user.getId() + "");
			attachments.put(Constant.Global.USER_NAME_FOR_DUBBO, user.getUserName());
			// ����dubbo��ʽ����
			RpcContext.getContext().setAttachments(attachments);
		}
*/
		return invoker.invoke(invocation);
	}

}