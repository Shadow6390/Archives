/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import model.MapaTermicoData;
import model.Simulacao;

/**
 * Class controller que controla a criação do mapa térmico.
 * @author DC_G2
 */
public class GerarMapaTermicoController {
    private Simulacao s;
    
    /**
     * Cria uma instância de {@link GerarMapaTermicoController} com os parâmetros
     * especificados.
     * @param s({@link model.Simulacao}) A simulação pretendida. 
     */
    public GerarMapaTermicoController(Simulacao s)
    {
        this.s=s;
    }
    /**
     * Retorna os dados do mapa térmico da simulação.
     * @return ({@link model.MapaTermicoData}) Os dados do mapa térmico.
     */
    public MapaTermicoData getMapaTermico()
    {
        return s.getMapaTermico();
    }
}
