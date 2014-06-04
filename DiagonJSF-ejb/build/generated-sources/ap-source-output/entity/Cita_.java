package entity;

import entity.Medico;
import entity.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-04T14:31:51")
@StaticMetamodel(Cita.class)
public class Cita_ { 

    public static volatile SingularAttribute<Cita, Integer> id;
    public static volatile SingularAttribute<Cita, Date> hora;
    public static volatile SingularAttribute<Cita, Usuario> idUsuario;
    public static volatile SingularAttribute<Cita, Date> fecha;
    public static volatile SingularAttribute<Cita, String> consulta;
    public static volatile SingularAttribute<Cita, Medico> idMedico;

}