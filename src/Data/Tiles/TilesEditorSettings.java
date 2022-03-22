package Data.Tiles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TilesEditorSettings {
    private TilesExport export;

    @JsonProperty("export")
    public TilesExport getExport() {
        return this.export;
    }

    @JsonProperty("export")
    public void setExport(final TilesExport export) {
        this.export = export;
    }
}
