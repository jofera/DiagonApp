package entity;

import entity.Cita;
import entity.Medico;
import entity.Paciente;
import entity.Tratamiento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-04T20:56:01")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, Paciente> paciente;
    public static volatile SingularAttribute<Usuario, String> direccion;
    public static volatile CollectionAttribute<Usuario, Cita> citaCollection;
    public static volatile CollectionAttribute<Usuario, Tratamiento> tratamientoCollection;
    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile CollectionAttribute<Usuario, Medico> medicoCollection;
    public static volatile SingularAttribute<Usuario, String> telefono;
    public static volatile SingularAttribute<Usuario, String> dni;
    public static volatile SingularAttribute<Usuario, Date> fechaNacimiento;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile CollectionAttribute<Usuario, Paciente> pacienteCollection;

}