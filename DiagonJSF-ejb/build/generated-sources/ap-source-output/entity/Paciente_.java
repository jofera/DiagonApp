package entity;

import entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-02T13:28:01")
@StaticMetamodel(Paciente.class)
public class Paciente_ { 

    public static volatile SingularAttribute<Paciente, Integer> id;
    public static volatile SingularAttribute<Paciente, Usuario> idUsuario;
    public static volatile SingularAttribute<Paciente, Integer> nuss;
    public static volatile SingularAttribute<Paciente, Usuario> idMedico;

}