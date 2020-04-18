package eu.fabiool.cnpj.file.parsing.lib.model;

public class AbstractInfoCnpj implements InfoCnpj {

	@Override
	public String[] getHeaders() {
		return InfoCnpj.headers(this.getClass());
	}

	@Override
	public String[] getValues() {
		return InfoCnpj.values(this);
	}

}
