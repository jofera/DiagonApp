package entity;

import entity.Medico;
import entity.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-04T14:31:51")
@StaticMetamodel(Tratamiento.class)
public class Tratamiento_ { 

    public static volatile SingularAttribute<Tratamiento, Integer> id;
    public static volatile SingularAttribute<Tratamiento, Usuario> idUsuario;
    public static volatile SingularAttribute<Tratamiento, String> descripcion;
    public static volatile SingularAttribute<Tratamiento, Date> fechaInicio;
    public static volatile SingularAttribute<Tratamiento, Integer> duracion;
    public static volatile SingularAttribute<Tratamiento, Medico> idMedico;

}