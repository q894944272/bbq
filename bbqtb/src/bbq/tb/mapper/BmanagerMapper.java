package bbq.tb.mapper;

import bbq.tb.entity.Bmanager;
import bbq.tb.entity.User;

public interface BmanagerMapper {

	Bmanager serch(Bmanager bmanager);

	void apply(Bmanager bmanager);

	void upapply(Bmanager bmanager);

	User[] serchDb(String bname);

}
