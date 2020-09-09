package model;

public enum IngredienteBasico {

    AZEITONA("Azeitona"),
    SAL("Sal"),
    ALHO("Alho"),
    LOURO("Louro"),
    MOLHOINGLES("Molho inglês"),
    PIMENTADOREINOPRETA("Pimenta do reino preta"),
    PIMENTADOREINOBRANCA("Pimenta do reino branca"),
    PIMENTAMALAGUETA("Pimenta malagueta"),
    AÇAFRAO("Açafrão"),
    MOLHODESOJA("Molho de soja"),
    ALECRIM("Alecrim"),
    TOMILHO("Tomilho"),
    NOZMOSCADA("Noz moscada"),
    PIMENTACALABRESA ("Pimenta calabresa "),
    CURRY("Curry"),
    OLEO("Óleo"),
    AZEITE("Azeite"),
    VINAGREDEVINHOTINTO("Vinagre de vinho tinto"),
    VINAGREDEVINHOBRANCO("Vinagre de vinho branco"),
    MEL("Mel"),
    FARINHADETRIGO("Farinha de trigo"),
    LEITE("Leite"),
    MANTEIGA("Manteiga"),
    QUEIJO("Queijo"),
    OVO("ovo"),
    LIMAO("limão"),
    TOMATE("tomate"),
    CENOURA("cenoura"),
    BATATA("batata"),
    CEBOLA("cebola"),
    BACON("bacon"),
    MOSTARDA("mostarda"),
    MOLHODETOMATE("molho de tomate"),
    CALDOCASEIRO("caldo caseiro"),
    PEITODEFRANGO("peito de frango"),
    COXASDEFRANGO("coxa de frango"),
    FILEDEPEIXE("filé de peixe"),
    CAMARAO("camarão"),
    ACUCAR("açúcar"),
    AMIDODEMILHO("amido de milho"),
    ARROZ("arroz"),
    FEIJAO("feijão"),
    MILHOEMLATA("milho em lata"),
    CREMEDELEITE("creme de leite"),
    PALITO("palito"),
    GUARDANAPO("guardanapo"),
    FILTROPLASTICO("filtro plástico"),
    PAPELALUMINIO("papel alumínio"),
    PAPELTOALHA("papel toalha");

    private String nome;

    IngredienteBasico(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
