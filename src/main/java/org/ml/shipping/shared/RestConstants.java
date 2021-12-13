package org.ml.shipping.shared;

public final class RestConstants {
    public static final String ML_API_URL = "api.mercadolibre.com/";
    public static final String BAD_PATTERN_MESSAGE = "Se debe cumplir el patrón {Area}-{Pasillo}-{Fila}-{Cara}";

    /***
     * DOCUMENTACIÓN
     */
    //OPERATIONS
    public static final String DOC_POST_PRODUCT_OP = "Con esta operación podemos agregar productos al depósito.";
    public static final String DOC_GET_PRODUCT_OP = "Con esta operación podemos obtener productos de un depósito y ubicación específico.";
    public static final String DOC_GET_FIND_PRODUCT_OP = "Con esta operación podemos buscar un producto específico en un depósito.";
    public static final String DOC_PUT_PRODUCT_OP = "Con esta operación podemos retirar productos del depósito.";
    // PARAMS
    public static final String DOC_LOCATION_PARAM = "Ubicación del depósito.";
    public static final String DOC_DEPOSIT_PARAM = "Dirección del depósito";
    public static final String DOC_PRODUCT_STOCK_PARAM = "Representación de un producto para ser guardado en el depósito - se valida el patrón del depósito.";
    public static final String DOC_PRODUCT_ID_PARAM = "ID del producto de la API de ML.";
}
