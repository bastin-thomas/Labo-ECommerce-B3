/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfreechart;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.util.ShapeUtilities;

/**
 *
 * @author Thomas
 */
public final class GUI extends javax.swing.JFrame {
    private Vector<String> Header;
    private LinkedList<MyRow> DataFrame;
    
    /**
     * Creates new form GUI
     */
    public GUI() throws FileNotFoundException, IOException, CsvValidationException {
        initComponents();
        Header = new Vector<String>();
        DataFrame = new LinkedList<MyRow>();
        
        CSVReader reader = new CSVReaderBuilder(new FileReader("E:\\HEPL\\R\\Labo-ECommerce-B3\\JFreeChart\\csv\\etude-agro-mais.csv")).build();
        CSVIterator it = new CSVIterator(reader);
        
        if(it.hasNext()){ it.next(); }
        int i = 0;
        while(it.hasNext()){
            String[] tmp = it.next();
            StringTokenizer st = new StringTokenizer(tmp[0],";");
            Vector<String> vec = new Vector<String>();
            
            while(st.hasMoreTokens()){
               vec.add(st.nextToken());
            }
            
            try {
                MyRow r = new MyRow(
                                        vec.get(1), vec.get(2), vec.get(3),  vec.get(4),
                                        vec.get(5), vec.get(6), vec.get(7),  vec.get(8), 
                                        vec.get(9), vec.get(10), vec.get(11), vec.get(12)
                                    );
                System.out.println(i + " : " + r);
                DataFrame.add(r);
                i++;
            } catch (Exception ex) {
                if(ex.getMessage().equalsIgnoreCase("NaN Value Error")) continue;
                
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        // 1. la relation entre Masse et Enracinement;
        showMasseToEnracinement();
        
        // 2. la relation entre Nb.grains et Masse.grains;
        //      XYSeries
        showNbgrainsMassegrains();
        
        // 3. la répartition de l'orientation de Parcelle pour l'échantillon considéré;
        //      PieChart
        showRepartitionParcelle();
        
        // 4. la relation entre Couleur et Enracinement.
        //      barChart
        showRelationCouleurEnracinement();
    }
    
    // 1. la relation entre Masse et Enracinement;
    public void showMasseToEnracinement(){
        DefaultBoxAndWhiskerCategoryDataset dataset = getMasseToEnracinement();        
        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart("Relation entre la Masse et l'Enracinement", "Enracinement", "Masse", dataset, true);
        ChartPanel chartPanel = new ChartPanel(chart);
        jInternalFrame1.setContentPane(chartPanel);
    }
    public DefaultBoxAndWhiskerCategoryDataset getMasseToEnracinement(){
        DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
        List list = new ArrayList();
        
        List faible = new LinkedList();
        List fort = new LinkedList();
        List moyen = new LinkedList();
        List TresFort = new LinkedList();
        
        for(MyRow r : DataFrame){
            if(r.Enracinement.equalsIgnoreCase("Faible"))   { faible.add(r.Masse);   continue; }
            if(r.Enracinement.equalsIgnoreCase("Moyen"))    { moyen.add(r.Masse);    continue; }
            if(r.Enracinement.equalsIgnoreCase("Fort"))     { fort.add(r.Masse);     continue; }
            if(r.Enracinement.equalsIgnoreCase("Tres.fort")){ TresFort.add(r.Masse); continue; }
        }
        dataset.add(faible, "Faible", "");
        dataset.add(moyen, "Moyen", "");
        dataset.add(fort, "Fort", "");
        dataset.add(TresFort, "TresFort", "");
        
        return dataset;
    }
    
    // 2. la relation entre Nb.grains et Masse.grains;
    //      XYSeries
    public void showNbgrainsMassegrains(){
        XYDataset dataset = getNbgrainsMassegrains();
        JFreeChart chart = ChartFactory.createScatterPlot("Relation entre le Nombre de grains et la Masse de grains", "Nombre de Grains", 
                                                          "Masse de Grains", dataset, PlotOrientation.HORIZONTAL, true, true, true);
        XYPlot xyPlot = (XYPlot) chart.getPlot();
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesShape(0,  new Ellipse2D.Double(-3, -3, 6, 6));
        renderer.setSeriesPaint(0, Color.BLACK);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        jInternalFrame2.setContentPane(chartPanel);
    }
    public DefaultXYDataset getNbgrainsMassegrains(){
        DefaultXYDataset dataset = new DefaultXYDataset();
        double[][] dataList = new double[2][DataFrame.size()];
        
        int i = 0;
        for(MyRow r : DataFrame){
            dataList[0][i] = r.NbGrains.doubleValue();
            dataList[1][i] = r.MasseGrains.doubleValue();
            i++;
        }
        dataset.addSeries("", dataList);
        return dataset;
    }
    
    // 3. la répartition de l'orientation de Parcelle pour l'échantillon considéré;
    //      PieChart
    public void showRepartitionParcelle(){
        PieDataset dataset = getRepartitionParcelle();
        JFreeChart chart = ChartFactory.createPieChart3D("Repartition de l'Orientation des Parcelles:", dataset, true, true, true);
        ChartPanel chartPanel = new ChartPanel(chart);
        jInternalFrame3.setContentPane(chartPanel);
    }
    
    public PieDataset getRepartitionParcelle(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        int i = 0;
        int countNord = 0;
        int countSud = 0;
        int countEst = 0;
        int countOuest = 0;
        
        for(MyRow r : DataFrame){
            if(r.Parcelle.equalsIgnoreCase("Nord")) { countNord++;  i++; continue; }
            if(r.Parcelle.equalsIgnoreCase("Sud"))  { countSud++;   i++; continue; }
            if(r.Parcelle.equalsIgnoreCase("Est"))  { countEst++;   i++; continue; }
            if(r.Parcelle.equalsIgnoreCase("Ouest")){ countOuest++; i++; continue; }
            
            System.out.println(i + "Parcelle Inconnue: " + r.Parcelle);
            i++;
            break;
        }
        
        dataset.setValue("Nord", countNord);
        dataset.setValue("Sud", countSud);
        dataset.setValue("Est", countEst);
        dataset.setValue("Ouest", countOuest);
        
        return dataset;
    }
    
    
    // 4. la relation entre Couleur et Enracinement.
    //      barChart
    public void showRelationCouleurEnracinement(){
        CategoryDataset dataset = getRelationCouleurEnracinement();
        JFreeChart chart = ChartFactory.createStackedBarChart("Relation entre Couleur et Enracinement", "Couleur", "Quantite",
                dataset, PlotOrientation.VERTICAL, true, true, true);
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(2, Color.YELLOW);
        plot.getRenderer().setSeriesPaint(1, Color.ORANGE);
        plot.getRenderer().setSeriesPaint(0, Color.RED);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        jInternalFrame4.setContentPane(chartPanel);
    }
    
    public CategoryDataset getRelationCouleurEnracinement(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        int i = 0;
        initDataset(dataset); //Init to 0;
                
        for(MyRow r : DataFrame){            
            if(r.Couleur.equalsIgnoreCase("Jaune")){
                AddElement(dataset, r, "Jaune");
                continue;
            }
            if(r.Couleur.equalsIgnoreCase("Jaune.Rouge")){
                AddElement(dataset, r, "Jaune.Rouge");
                continue;
            }
            if(r.Couleur.equalsIgnoreCase("Rouge")){
                AddElement(dataset, r, "Rouge");
                continue;
            }
            
            System.out.println("Parcelle Inconnue: " + r.Couleur);
            break;
        }
        
        convertToPercent(dataset);
        return dataset;
    }
    
    private void AddElement(DefaultCategoryDataset dataset, MyRow r, String m){
        if(r.Enracinement.equalsIgnoreCase("Faible")){
            int i = dataset.getValue(m, "Faible").intValue();
            i++;
            dataset.addValue(i, m, "Faible");
        }
        
        if(r.Enracinement.equalsIgnoreCase("Moyen")){
            int i = dataset.getValue(m, "Moyen").intValue();
            i++;
            dataset.addValue(i, m, "Moyen");
        }
        
        if(r.Enracinement.equalsIgnoreCase("Fort")){
            int i = dataset.getValue(m, "Fort").intValue();
            i++;
            dataset.addValue(i, m, "Fort");
        }
        
        if(r.Enracinement.equalsIgnoreCase("Tres.fort")){
            int i = dataset.getValue(m, "TresFort").intValue();
            i++;
            dataset.addValue(i, m, "TresFort");
        }
    }
    
    private void initDataset(DefaultCategoryDataset dataset){
        dataset.addValue(0, "Rouge",        "Faible");
        dataset.addValue(0, "Jaune.Rouge",  "Faible");
        dataset.addValue(0, "Jaune",        "Faible");
        
        dataset.addValue(0, "Rouge",        "Moyen");
        dataset.addValue(0, "Jaune.Rouge",  "Moyen");
        dataset.addValue(0, "Jaune",        "Moyen");
        
        dataset.addValue(0, "Rouge",        "Fort");
        dataset.addValue(0, "Jaune.Rouge",  "Fort");
        dataset.addValue(0, "Jaune",        "Fort");
        
        
        dataset.addValue(0, "Rouge",        "TresFort");
        dataset.addValue(0, "Jaune.Rouge",  "TresFort");
        dataset.addValue(0, "Jaune",        "TresFort");
    }
    
    private void convertToPercent(DefaultCategoryDataset dataset){
        valueToPercent(dataset, "Faible");
        valueToPercent(dataset, "Moyen");
        valueToPercent(dataset, "Fort");
        valueToPercent(dataset, "TresFort");
    }
    
    private void valueToPercent(DefaultCategoryDataset dataset, String m){
        int total = 0;
        int j=0, jr=0, r=0;
        
        j =  dataset.getValue("Jaune",        m).intValue();
        jr = dataset.getValue("Jaune.Rouge",  m).intValue();
        r =  dataset.getValue("Rouge",        m).intValue();
        
        total = j + jr + r;
        
        dataset.addValue((double)j/(double)total, "Jaune",        m);
        dataset.addValue((double)jr/(double)total, "Jaune.Rouge", m);
        dataset.addValue((double)r/(double)total, "Rouge",        m);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jInternalFrame3 = new javax.swing.JInternalFrame();
        jInternalFrame4 = new javax.swing.JInternalFrame();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        getContentPane().add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 460));

        jInternalFrame2.setVisible(true);

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        getContentPane().add(jInternalFrame2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 540, 460));

        jInternalFrame3.setVisible(true);

        javax.swing.GroupLayout jInternalFrame3Layout = new javax.swing.GroupLayout(jInternalFrame3.getContentPane());
        jInternalFrame3.getContentPane().setLayout(jInternalFrame3Layout);
        jInternalFrame3Layout.setHorizontalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );
        jInternalFrame3Layout.setVerticalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        getContentPane().add(jInternalFrame3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 540, 440));

        jInternalFrame4.setVisible(true);

        javax.swing.GroupLayout jInternalFrame4Layout = new javax.swing.GroupLayout(jInternalFrame4.getContentPane());
        jInternalFrame4.getContentPane().setLayout(jInternalFrame4Layout);
        jInternalFrame4Layout.setHorizontalGroup(
            jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );
        jInternalFrame4Layout.setVerticalGroup(
            jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        getContentPane().add(jInternalFrame4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 470, 540, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GUI().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                } catch (CsvValidationException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JInternalFrame jInternalFrame3;
    private javax.swing.JInternalFrame jInternalFrame4;
    // End of variables declaration//GEN-END:variables
}
