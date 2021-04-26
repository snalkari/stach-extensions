package com.factset.protobuf.stach.extensions;

import com.factset.protobuf.stach.extensions.models.StachVersion;
import com.factset.protobuf.stach.extensions.v2.RowOrganizedStachBuilder;


public class StachExtensionFactory {

    public static StachExtensionBuilder getColumnOrganizedBuilder(StachVersion version) {

        switch (version) {
            case V1:
                return new com.factset.protobuf.stach.extensions.v1.ColumnOrganizedStachBuilder();
            case V2:
                return new com.factset.protobuf.stach.extensions.v2.ColumnOrganizedStachBuilder();
            default:
                return null;
        }
    }

    public static StachExtensionBuilderRow getRowOrganizedBuilder() {
        return new RowOrganizedStachBuilder();
    }
}
