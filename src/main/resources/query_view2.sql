create view itxx as(select itx.item_cinterno,sum(itx.caprobada) as totCompras
from item it
left join itmxorden itx on it.cinterno = itx.item_cinterno
group by itx.item_cinterno);
