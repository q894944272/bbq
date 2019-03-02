package bbq.tb.mapper;

import bbq.tb.entity.Bmanager;
import bbq.tb.entity.User;

public interface SmanagerMapper {
	Bmanager serch(Bmanager bmanager);

	void apply(Bmanager bmanager);

	void upapply(Bmanager bmanager);

	User[] serchSb(String bname);
}
