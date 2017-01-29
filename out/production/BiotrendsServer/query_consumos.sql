SELECT it.cinterno as cinterno, it.inventario as inventario, it.descripcion as descripcion,
 it.presentacion as presentacion, it.cantidad as cantidad, it.precio as precio, it.ccalidad as ccalidad,
  it.cesp as cesp, itx.totCompras as totCompras, d.totdescargos as totDescargos 
FROM item it LEFT JOIN descar d
ON it.cinterno = d.cinterno, item it2 LEFT JOIN itxx itx
ON it2.cinterno = itx.item_cinterno
WHERE it.cinterno = it2.cinterno;
