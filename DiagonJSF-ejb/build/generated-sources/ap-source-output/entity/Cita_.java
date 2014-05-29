package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-05-29T11:33:02")
@StaticMetamodel(Cita.class)
public class Cita_ { 

    public static volatile SingularAttribute<Cita, Integer> id;
    public static volatile SingularAttribute<Cita, Integer> idPaciente;
    public static volatile SingularAttribute<Cita, Integer> idDoctor;
    public static volatile SingularAttribute<Cita, Date> fecha;
    public static volatile SingularAttribute<Cita, String> consulta;

}