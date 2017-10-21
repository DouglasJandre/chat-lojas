package br.com.ykp.americanas;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Conversation {
	private ConversationService service;
	private Map<String, Object> context;

	public Conversation() {
		init();
	}

	public void init() {
		service = new ConversationService(ConversationService.VERSION_DATE_2016_09_20,
				"af4f73a7-cf6b-4363-8ee5-96b3a19dc623", "IZp4svTRAK47");
		context = null;
	}

	// String = MessageResponse
	public MessageResponse conversar(String frase) {
		MessageRequest request = new MessageRequest.Builder().context(context).inputText(frase).build();
		MessageResponse resp = service.message("d0457475-8bef-420c-b582-7e3fad7ba87a", request).execute();
		context = resp.getContext();
		System.out.println(resp.toString());
		//pegar variavel no contexto resp.getContext().get("") != null
//		return resp.toString();		
//		context = resp.getContext();
		
		return resp;	
		
	}

	private Object console() {
		// TODO Auto-generated method stub
		return null;
	}
}
