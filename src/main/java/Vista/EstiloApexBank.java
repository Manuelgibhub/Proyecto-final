/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

/** Tema visual común de todas las ventanas de Apex Bank. */
public final class EstiloApexBank {
    private EstiloApexBank() { }

    public static void aplicarTema() {
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("Button.foreground", new Color(51, 51, 51));
        UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("TextField.background", Color.WHITE);
        UIManager.put("Table.background", Color.WHITE);
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 13));
    }
}