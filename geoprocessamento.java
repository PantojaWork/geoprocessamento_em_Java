import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class GeoprocessingSystem {

    private static final String SHAPEFILE_PATH = "/path/to/shapefile";

    public static void main(String[] args) throws IOException, FactoryException {
        // Carrega o shapefile
        DataStore dataStore = DataStoreFinder.getDataStore(new File(SHAPEFILE_PATH));
        FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = dataStore.getFeatureSource("layer");

        // Obtém a coleção de features
        FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection = featureSource.getFeatures();

        // Itera sobre a coleção de features
        FeatureIterator<SimpleFeature> featureIterator = featureCollection.features();
        while (featureIterator.hasNext()) {
            SimpleFeature feature = featureIterator.next();

            // Obtém as coordenadas do feature
            CoordinateReferenceSystem crs = CRS.decode("EPSG:4326");
            org.locationtech.jts.geom.Point point = (org.locationtech.jts.geom.Point) feature.getAttribute("geometry");
            double latitude = point.getX();
            double longitude = point.getY();

            // Imprime as coordenadas do feature
            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);
        }
    }
}
