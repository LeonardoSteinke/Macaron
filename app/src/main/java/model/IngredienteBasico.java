package model;

public enum IngredienteBasico {

    SAL("sal"),
    ALHO("alho"),
    LOURO("louro"),
    MOLHOINGLES("molho inglês"),
    PIMENTADOREINOPRETA("pimenta do reino preta"),
    PIMENTADOREINOBRANCA("pimenta do reino branca"),
    PIMENTAMALAGUETA("pimenta malagueta"),
    AÇAFRAO("açafrão"),
    MOLHODESOJA("molho de soja"),
    ALECRIM("alecrim"),
    TOMILHO("tomilho"),
    NOZMOSCADA("noz moscada"),
    PIMENTACALABRESA ("pimenta calabresa "),
    CURRY("curry"),
    OLEO("óleo"),
    AZEITE("azeite"),
    VINAGREDEVINHOTINTO("vinagre de vinho tinto"),
    VINAGREDEVINHOBRANCO("vinagre de vinho branco"),
    MEL("mel"),
    FARINHADETRIGO("farinha de trigo"),
    LEITE("leite"),
    MANTEIGA("manteiga"),
    QUEIJO("queijo"),
    OVO("ovo"),
    LIMAO("limão"),
    TOMATE("tomate"),
    CENOURA("cenoura"),
    BATATA("batata"),
    CEBOLA("cebola"),
    BACON("bacon"),
    MOSTARDA("mostarda"),
    MOLHODETOMATE("molho de tomate"),
    AZEITONA("azeitona"),
    CALDOCASEIRO("caldo caseiro"),
    PEITODEFRANGO("peito de frango"),
    COXASDEFRANGO("coxas de frango"),
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
