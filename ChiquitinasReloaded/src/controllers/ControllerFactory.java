/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import servicios.Servicio;

/**
 *
 * @author santialfonso
 */
public abstract class ControllerFactory {
    
    public abstract Servicio CrearServicio();
}
