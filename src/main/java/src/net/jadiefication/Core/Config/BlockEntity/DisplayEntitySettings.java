package src.net.jadiefication.Core.Config.BlockEntity;

import java.util.List;

public record DisplayEntitySettings(String id, BlockStateSettings settings, List<Float> transformation) {
}
