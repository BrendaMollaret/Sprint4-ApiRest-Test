package desarrollo.sprint4.apiresttest.Repository;

import desarrollo.sprint4.apiresttest.Entity.ArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado,Long>{

    //query
    //busqueda de productos que coincidan con el nombre
    @Query(
            value = "SELECT * FROM articulo_manufacturado WHERE articulo_manufacturado.nombre_articulo_manufacturado LIKE %:filtro%",
            nativeQuery = true
    )
    Page<ArticuloManufacturado> searchByNombre(
            @Param("filtro") String filtro,
            Pageable pageable
    );

    //busqueda de productos por el precio de venta
    @Query(
            value = "SELECT * FROM articulo_manufacturado WHERE articulo_manufacturado.precio_venta LIKE %:precioVenta%",
            nativeQuery = true
    )
    Page<ArticuloManufacturado> searchByPrecioVenta(
            @Param("precioVenta") BigDecimal precioVenta,
            Pageable pageable
    );

    //busqueda de precios por un rango de precios
    @Query(
            value = "SELECT * FROM articulo_manufacturado " +
                    "WHERE articulo_manufacturado.precio_venta BETWEEN :precioMinimo AND :precioMaximo",
            nativeQuery = true
    )
    Page<ArticuloManufacturado> searchByPrecioVentaRange(
            @Param("precioMinimo") BigDecimal precioMinimo,
            @Param("precioMaximo") BigDecimal precioMaximo,
            Pageable pageable
    );

    //busqueda de productos por categoria
    @Query(value = "SELECT am FROM ArticuloManufacturado am JOIN am.categoriaArticuloManufacturado c WHERE c.nombreCategoriaArticuloManufacturado LIKE %:nombreCategoria%")
    Page<ArticuloManufacturado> searchByCategoriaNombre(
            @Param("nombreCategoria") String nombreCategoria,
            Pageable pageable
    );


}


