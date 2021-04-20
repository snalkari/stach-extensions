package com.factset.protobuf.stach.extensions.tests;

import com.factset.protobuf.stach.extensions.models.Stach2Type;
import com.factset.protobuf.stach.extensions.models.StachVersion;
import com.factset.protobuf.stach.v2.PackageProto;
import com.factset.protobuf.stach.extensions.v2.ColumnOrganizedStachExtension;
import com.factset.protobuf.stach.extensions.ExtensionFactory;
import com.factset.protobuf.stach.extensions.models.TableData;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class V2ColumnTests {

    PackageProto.Package _package;
    ColumnOrganizedStachExtension colStachExtension;

    List<String> row1 = Arrays.asList("total0","group1","group2", "Port.+Weight", "Bench.+Weight", "Difference");
    List<String> row2 = Arrays.asList("Total","","", "100.0", "--", "100.0");

    @BeforeTest
    public void setup() throws IOException {
        Path workingDirectory = Paths.get("src", "test", "java", "resources");
        String input = new String(Files.readAllBytes(Paths.get(workingDirectory.toString(), "V2Column.json")));
        colStachExtension = (ColumnOrganizedStachExtension) ExtensionFactory.getStachExtension(StachVersion.V2, Stach2Type.ColumnOrganized);
        _package = (PackageProto.Package) colStachExtension.convertToPackage(input);
    }

    @Test
    public void testConvert(){
        System.out.println(_package.getPrimaryTableIdsList().get(0));
        List<TableData> tableDataList = colStachExtension.convertToTable(_package);

        Assert.assertEquals(row1, tableDataList.get(0).getRows().get(0).getCells());
        Assert.assertEquals(true, tableDataList.get(0).getRows().get(0).isHeader());

        Assert.assertEquals(row2, tableDataList.get(0).getRows().get(1).getCells());
        Assert.assertEquals(false, tableDataList.get(0).getRows().get(1).isHeader());

    }

}
