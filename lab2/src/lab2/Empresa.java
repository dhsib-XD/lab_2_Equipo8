
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

/**
 *
 * @author andre
 */

import java.util.ArrayList;

public class Empresa {
    private ArrayList<Empleado> Empleados = new ArrayList<>();
    
    public Empleado Buscar(String codigo) {
        if (codigo == null) {
            return null;
        }
        
        for (Empleado emp : Empleados) {
            if (codigo.equals(emp.codigo))
                return emp;
        }
        
        return null;
    }

    protected void registrarEmpleado() {
        
    }

    protected void registrarHoras() {
    }

    protected void registrarVenta() {
    }

    protected void actualizarFinContrato() {
    }
}

