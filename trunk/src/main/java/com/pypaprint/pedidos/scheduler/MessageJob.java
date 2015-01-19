package com.pypaprint.pedidos.scheduler;

import java.util.Iterator;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.pypaprint.pedidos.domain.Pedido;

public class MessageJob extends QuartzJobBean {
	@Autowired
	Pedido pedido;
	MessageTask messageTask;
	
	
	public MessageTask getMessageTask() {
		return messageTask;
	}


	public void setMessageTask(MessageTask messageTask) {
		this.messageTask = messageTask;
	}


	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		List<Pedido> listPedido = pedido.findAllPedidoes();
		Iterator<Pedido> iterator = listPedido.iterator();
		while (iterator.hasNext()) {
			messageTask.consultarPedidos(iterator.next());
		}
		
	}

}
