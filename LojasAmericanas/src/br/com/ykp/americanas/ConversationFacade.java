package br.com.ykp.americanas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import br.com.ykp.americanas.controller.ValidaDados;

@RestController
public class ConversationFacade {
	@Autowired
	private Conversation conversation;
	
	@Autowired
	private ValidaDados validaDados;

		@GetMapping("/conversa/{frase}")
		public String conversar(@PathVariable String frase) {
			MessageResponse resp = conversation.conversar(frase);
			String valida = (String) resp.getContext().get("valida");
			if (valida != null && valida.equals("email")) {
				boolean valido = validaDados.validEmail(resp.getContext().get("email").toString());
				System.out.println("Email - " +resp.getContext().get("email").toString()+"\nÉ válido? " + valido);
				if(valido){
					resp.getContext().put("valido", true);
				}else{
					resp.getContext().put("valido", false);
				}
				//código da validation here
				//System.out.println("Validar email do batman");
			}else if (valida != null && valida.equals("telefone")){
				boolean valido = validaDados.validTelefone(resp.getContext().get("telefone").toString());
				if(valido){
					resp.getContext().put("valido", true);
				}else{
					resp.getContext().put("valido", false);
				}
			}else if (valida != null && valida.equals("cpf")){
				boolean valido = validaDados.validCPF(resp.getContext().get("cpf").toString());
				if(valido){
					resp.getContext().put("valido", true);
				}else{
					resp.getContext().put("valido", false);
				}
			}
			return resp.getTextConcatenated("");
		}
//		@GetMapping("/conversa/{frase}")
//		public String conversar(@PathVariable String frase) {
//			return conversation.conversar(frase);
//		}
	}
