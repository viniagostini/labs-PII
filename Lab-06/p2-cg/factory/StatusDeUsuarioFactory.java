package factory;

import exceptions.LogicaDeNegociosExecption;
import usuario.StatusNoob;
import usuario.StatusDeUsuario;
import usuario.TipoDeUsuario;
import usuario.StatusVeterano;


/* 115110107 - Vinicius Alencar Agostini: LAB 7 - Turma 3 */
/**
 * Classe cuja unica resposabilidade esta na criacao de Usuarios
 * 
 * @author Vinicius A. Agostini
 */
public class StatusDeUsuarioFactory {

	
	/**
	 * Metodo que retorna um status de usuario baseado
	 * no tipo recebido.
	 * 
	 * @param TipoDeUsuario - Tipo do usuario.
	 * @return
	 * @throws LogicaDeNegociosExecption
	 */
	public StatusDeUsuario criaStatusDeUsuario(TipoDeUsuario tipoUsuario) throws LogicaDeNegociosExecption{
		
		StatusDeUsuario novoStatus;
		
		switch (tipoUsuario) {
		case NOOB:
			
			novoStatus = new StatusNoob();
			
			break;
		case VETERANO:
			
			novoStatus = new StatusVeterano();
			
			break;
		default:
			throw new LogicaDeNegociosExecption("Tipo de usuario invalido");
		}
		
		return novoStatus;
	}

}
