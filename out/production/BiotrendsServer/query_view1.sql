create view descar as(select it.cinterno, it.inventario, it.descripcion, it.presentacion, it.cantidad, it.precio, it.ccalidad, it.cesp, sum(de.cantidad) as totdescargos
from item it
left join descargo de on it.cinterno = de.cinterno
where timestamp(de.fecha) > ?
group by de.cinterno);
