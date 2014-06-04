package entity;

import entity.Medico;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-04T13:23:30")
@StaticMetamodel(Especialidad.class)
public class Especialidad_ { 

    public static volatile SingularAttribute<Especialidad, Integer> id;
    public static volatile SingularAttribute<Especialidad, String> nombre;
    public static volatile CollectionAttribute<Especialidad, Medico> medicoCollection;

}