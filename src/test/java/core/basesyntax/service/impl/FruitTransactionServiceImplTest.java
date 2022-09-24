package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.FruitTransactionService;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FruitTransactionServiceImplTest extends FruitTransaction {
    FruitTransactionService transactionService;
    private List<String> list;
    private List<FruitTransaction> actual;

    @Before
    public void setUp() {
        transactionService = new FruitTransactionServiceImpl();
        list = List.of("type,fruit,quantity", "b,banana,50", "b,apple,30",
                "p,apple,20", "s,banana,10", "p,banana,24", "r,banana,50");
        actual = new ArrayList<>();
        actual.add(new FruitTransaction(Operation.BALANCE, "banana", 50));
        actual.add(new FruitTransaction(Operation.BALANCE, "apple", 30));
        actual.add(new FruitTransaction(Operation.PURCHASE, "apple", 20));
        actual.add(new FruitTransaction(Operation.SUPPLY, "banana", 10));
        actual.add(new FruitTransaction(Operation.PURCHASE, "banana", 24));
        actual.add(new FruitTransaction(Operation.RETURN, "banana", 50));
    }

    @Test (expected = RuntimeException.class)
    public void TransactionService_ParseNull_NotOK() {
        transactionService.parseFruitTransactions(null);
    }

    @Test
    public void TransactionService_ParseData_OK() {
        List<FruitTransaction> expected = transactionService.parseFruitTransactions(list);
        assertEquals(expected, actual);
    }
}