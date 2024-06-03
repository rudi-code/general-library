package id.co.bjj.library.general.persistence.dtos;

import java.util.ArrayList;
import java.util.List;

public class ListBlacklistTokenDto {
	private List<String> listToken = new ArrayList<>();

	public void addListToken(List<String> listToken) {
		this.listToken.addAll(listToken);
	}

	public void addToken(String token) {
		this.listToken.add(token);
	}

	public List<String> getListToken() {
		return listToken;
	}
}
