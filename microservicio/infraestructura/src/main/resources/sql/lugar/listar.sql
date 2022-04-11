select id_lugar,estado
from lugar
where estado = :estado
order by estado;