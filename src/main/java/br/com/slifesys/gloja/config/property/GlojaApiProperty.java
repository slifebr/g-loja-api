package br.com.slifesys.gloja.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("loja")
public class GlojaApiProperty {

	private String originPermitida = "http://localhost:4200";
	
	private final Seguranca seguranca = new Seguranca();
	
	
	/**
	 * @return the originPermitida
	 */
	public String getOriginPermitida() {
		return originPermitida;
	}


	/**
	 * @param originPermitida the originPermitida to set
	 */
	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}


	/**
	 * @return the seguranca
	 */
	public Seguranca getSeguranca() {
		return seguranca;
	}


	public static class Seguranca {
		private boolean enableHttps;

		/**
		 * @return the enableHttps
		 */
		public boolean isEnableHttps() {
			return enableHttps;
		}

		/**
		 * @param enableHttps the enableHttps to set
		 */
		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
		
		
			
	}
}
