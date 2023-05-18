package core.basesyntax.service;

import core.basesyntax.dao.FruitsDao;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.process.FruitDataProcess;
import core.basesyntax.process.FruitReportMaker;
import java.util.List;

public class FruitShopServiceImpl implements FruitShopService {
    private FruitsDao fruitsDao;
    private FruitDataProcess fruitDataProcess;
    private FruitReportMaker fruitReportMaker;

    public FruitShopServiceImpl(FruitsDao fruitsDao,
                                FruitDataProcess fruitDataProcess,
                                FruitReportMaker fruitReportMaker) {
        this.fruitsDao = fruitsDao;
        this.fruitDataProcess = fruitDataProcess;
        this.fruitReportMaker = fruitReportMaker;
    }

    @Override
    public String serviceFruitsShop(String fromFile, String toFile) {
        List<FruitTransaction> fruitsList = fruitsDao.getFruitsData(fromFile);
        fruitDataProcess.processFruitData(fruitsList);
        String fruitReport = fruitReportMaker.makeFruitReport();
        fruitsDao.writeToFile(fruitReport, toFile);
        return fruitReport;
    }
}
