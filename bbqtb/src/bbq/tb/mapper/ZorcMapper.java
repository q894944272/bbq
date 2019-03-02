package bbq.tb.mapper;

import bbq.tb.entity.Zorcc;

public interface ZorcMapper {

	int findzorcnum(Zorcc zorcc)throws Exception;

	void savezorcnum(Zorcc zorcc);

	void deletezorc(Zorcc zorcc);

}
