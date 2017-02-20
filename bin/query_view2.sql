create view itxx as(select itx.item_cinterno, sum(itx.caprobada) as totCompras
from  itmxorden itx , solicitud_pr sol
where  sol.num_sol = itx.numSolAsociado
and sol.fecha >= ?
group by itx.item_cinterno, sol.num_sol);