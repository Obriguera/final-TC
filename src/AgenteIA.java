import smile.classification.DecisionTree;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.data.vector.DoubleVector;
import smile.data.vector.IntVector;
import smile.base.cart.SplitRule;

public class AgenteIA {
    private DecisionTree modelo;

    public AgenteIA() {
        entrenarModelo();
    }

    private void entrenarModelo() {
        System.out.println("\u001B[34m[IA] Entrenando Agente de Optimización (Árbol de Decisión)...\u001B[0m");

        // Una fila por instrucción candidata: [asignaciones, lecturas, dentroDeLoop]
        // Dataset expandido: Garantiza el aislamiento algorítmico de variables muertas
        double[] asignaciones = {1, 2, 1, 1, 3, 1, 5, 2, 1, 4};
        double[] lecturas     = {0, 5, 1, 0, 0, 2, 0, 1, 0, 8};
        double[] enLoop       = {0, 1, 0, 1, 0, 0, 1, 1, 0, 0};
        int[] etiqueta        = {1, 0, 0, 1, 1, 0, 1, 0, 1, 0};

        DataFrame data = DataFrame.of(
                DoubleVector.of("asignaciones", asignaciones),
                DoubleVector.of("lecturas", lecturas),
                DoubleVector.of("enLoop", enLoop),
                IntVector.of("eliminar", etiqueta)
        );

        Formula formula = Formula.lhs("eliminar");

        // SplitRule.GINI, profundidad máx 4, máx 8 hojas, mínimo 1 instancia por nodo
        this.modelo = DecisionTree.fit(formula, data, SplitRule.GINI, 4, 8, 1);

        System.out.println("\u001B[34m[IA] Modelo entrenado con éxito.\u001B[0m");
    }

    public boolean esCodigoMuerto(int asignaciones, int lecturas, boolean enLoop) {
        // predict() de DataFrameClassifier espera una Tuple, no un double[]
        smile.data.Tuple instancia = smile.data.Tuple.of(
                new Object[]{ (double) asignaciones, (double) lecturas, enLoop ? 1.0 : 0.0 },
                modelo.schema()
        );
        int prediccion = modelo.predict(instancia);
        return prediccion == 1;
    }
}