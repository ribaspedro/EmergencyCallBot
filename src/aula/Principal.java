package aula;

import java.util.List;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
public class Principal {

	public static void main(String[] args) {

		// Cria��o do objeto bot com as informa��es de acesso
		TelegramBot bot = TelegramBotAdapter.build("XXXXXX-XXXXXX-XXXXXX-XXXXXX-XXXXXX");

		// objeto respons�vel por receber as mensagens
		GetUpdatesResponse updatesResponse;
		// objeto respons�vel por gerenciar o envio de respostas
		SendResponse sendResponse;
		// objeto respons�vel por gerenciar o envio de a��es do chat
		BaseResponse baseResponse;

		// controle de off-set, isto �, a partir deste ID ser� lido as mensagens
		// pendentes na fila
		int m = 0;
		boolean continuar = true;
		boolean responde = true;
		String resp = null;
		boolean bola = true;
		// loop infinito pode ser alterado por algum timer de intervalo curto
		do {

			// executa comando no Telegram para obter as mensagens pendentes a
			// partir de um off-set (limite inicial)
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

			// lista de mensagens
			List<Update> updates = updatesResponse.updates();

			// an�lise de cada a��o da mensagem
			for (Update update : updates) {

				// atualiza��o do off-set
				m = update.updateId() + 1;

				System.out.println("Recebendo mensagem: " + update.message().text());
				while (bola){
				// envio de "Escrevendo" antes de enviar a resposta
				baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
				// verifica��o de a��o de chat foi enviada com sucesso
				System.out.println("Resposta de Chat Action Enviada? " + baseResponse.isOk());
				
				Keyboard keyboard = new ReplyKeyboardMarkup(
				        new KeyboardButton[]{
				                new KeyboardButton("Queimaduras"),
				                new KeyboardButton("Fraturas"),
				                new KeyboardButton("Sangramentos")
				        }
				);
				
				sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "ol�"));
				sendResponse = bot.execute(
						new SendMessage(update.message().chat().id(), "Bem-vindo ao Bot de Primeiros Socorros!"));
				sendResponse = bot.execute(new SendMessage(update.message().chat().id(),
				 	"Cod: 1 : Queimaduras\n"
					+ "Cod: 2 : Fraturas\n"
					+ "Cod: 3 : Sangramentos\n"
					+ "Cod: 4 : Envenenamentos\n").replyMarkup(keyboard)
);
				
				
				 
				

				
				bola = false;
				}
				 resp = update.message().text();
				if (resp != null){
				switch (resp) {
				
				
				case ("Queimaduras"):
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "� Isolar a v�tima do agente causador do acidente (fogo ou subst�ncias qu�micas lesivas, por exemplo, um �cido)\n"
+"� Lavar a �rea queimada com �gua corrente limpa\n"
+"� Se houver tecido da vestimenta aderido ao ferimento, este pode ser retirado de forma a n�o aumentar a les�o, no instante em que se estiver lavando o local\n"
+"� N�o colocar �gua fria, gelo ou sab�o sobre o ferimento\n"
+"� Proteja o local com pano e tecido limpo, mantendo a regi�o mais elevada em rela��o ao resto do corpo para evitar incha�o\n"
+"� Procure um centro de queimadura mais pr�ximo de sua resid�ncia."));
				
					break;
				case ("Fraturas"):
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "� Se a fratura for exposta, cubra o ferimento com gaze ou pano limpo. Em hip�tese alguma tente realinhar o membro ou retornar o osso, isso pode agravar a situa��o"
+"� Imobilize a regi�o lesada com t�bua, papel�o ou madeira, envolvendo uma faixa\n"
+"� Em caso de sangramento incessante (hemorragia), realize uma leve compress�o com pano limpo."));
				
					break;
				case ("Sangramentos"):
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "� Colocar um pano ou papel limpo no ferimento\n"
+"� Fazer press�o sobre o local, o suficiente para deter o sangramento.\n" 
+"� Eleve o bra�o ou a perna da v�tima, mantendo a press�o sobre o ferimento.\n" 
+"� Levar a v�tima ao pronto-socorro."));
	
					break;
				}
				// mudei
				// do{
				// if(resp!=null){

				// }
				// }while(true);

				// envio da mensagem de resposta
				// sendResponse = bot.execute(new
				// SendMessage(update.message().chat().id(),"pau"));
				// verifica��o de mensagem enviada com sucesso
				// System.out.println("Mensagem Enviada?" +sendResponse.isOk());

				}
			}
		} while (resp != null);

	}

}