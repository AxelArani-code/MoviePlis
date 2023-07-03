package com.example.movieplus.Model;

import java.io.Serializable;

public class Movie implements Serializable {
    private String titulo,fecha,duracion,genero,portada, estrella, descripsion,video;

    public Movie() {

    }

    public Movie(String titulo, String fecha, String duracion, String genero, String portada, String estrella, String descripsion, String video) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.duracion = duracion;
        this.genero = genero;
        this.portada = portada;
        this.estrella = estrella;
        this.descripsion = descripsion;
        this.video = video;
    }

    public String getDescripsion() {
        return descripsion;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setDescripsion(String descripsion) {
        this.descripsion = descripsion;
    }

    public String getEstrella() {
        return estrella;
    }

    public void setEstrella(String estrella) {
        this.estrella = estrella;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
}
