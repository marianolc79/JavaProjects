package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-05T22:11:55.337+0200")
@StaticMetamodel(Region.class)
public class Region_ {
	public static volatile SingularAttribute<Region, Long> regionId;
	public static volatile SingularAttribute<Region, String> regionName;
	public static volatile ListAttribute<Region, Country> countries;
}
