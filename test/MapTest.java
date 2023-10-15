import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Kamilia Kamal Arifin and Jordyn Liegl
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     * Test the constructor with no argument.
     */
    @Test
    public void testConstructorNoArgument() {
        /*
         * Set up the variables and call the method under test.
         */
        Map<String, String> size = this.constructorRef();
        Map<String, String> sizeExpected = this.constructorTest();

        /*
         * Assert of the values of the variables match expectations.
         */
        assertEquals(sizeExpected, size);
    }

    //AM I SUPPOSED TO HAVE MORE TEST CASES FOR THE CONSTRUCTOR?

    /**
     * Test the kernel method add with a boundary case.
     */
    @Test
    public void testAddBoundary() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef();
        Map<String, String> expectedMap = this.createFromArgsTest("a", "1");

        /*
         * Call method under test.
         */
        map.add("a", "1");

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(expectedMap, map);
    }

    /**
     * Test the kernel method add with a routine case.
     */
    @Test
    public void testAddRoutine() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef("a", "1", "b", "2",
                "c", "3");
        Map<String, String> expectedMap = this.createFromArgsTest("a", "1", "b",
                "2", "c", "3", "d", "4");

        /*
         * Call method under test.
         */
        map.add("d", "4");

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(expectedMap, map);
    }

    /**
     * Test the kernel method add with a challenging case.
     */
    @Test
    public void testAddChallenging() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef("a", "1", "b", "2",
                "c", "3", "d", "4");
        Map<String, String> expectedMap = this.createFromArgsTest("a", "1", "b",
                "2", "c", "3", "d", "4", "", "0");

        /*
         * Call method under test.
         */
        map.add("", "0");

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(expectedMap, map);

    }

    /**
     * Test the kernel method remove with a boundary case.
     */
    @Test
    public void testRemoveBoundary() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef("a", "1");
        Map<String, String> expectedMap = this.createFromArgsTest();

        /*
         * Call method under test.
         */
        map.remove("a");

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(expectedMap, map);

    }

    /**
     * Test the kernel method remove with a routine case.
     */
    @Test
    public void testRemoveRoutine() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef("a", "1", "b", "2",
                "c", "3", "d", "4");
        Map<String, String> expectedMap = this.createFromArgsTest("a", "1", "b",
                "2", "c", "3");

        /*
         * Call method under test.
         */
        map.remove("d");

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(expectedMap, map);

    }

    /**
     * Test the kernel method remove with a challenging case.
     */
    @Test
    public void testRemoveChallenging() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef("a", "1", "b", "2",
                "c", "3", "d", "4");
        Map<String, String> expectedMap = this.createFromArgsTest();

        /*
         * Call method under test.
         */
        Map.Pair<String, String> pair = map.remove("d");
        Map.Pair<String, String> pair2 = map.remove("c");
        Map.Pair<String, String> pair3 = map.remove("b");
        Map.Pair<String, String> pair4 = map.remove("a");

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals("d", pair.key());
        assertEquals("4", pair.value());

        assertEquals("c", pair2.key());
        assertEquals("3", pair2.value());

        assertEquals("b", pair3.key());
        assertEquals("2", pair3.value());

        assertEquals("a", pair4.key());
        assertEquals("1", pair4.value());
        assertEquals(expectedMap, map);
    }

    /**
     * Test the kernel method removeAny with a boundary case.
     */
    @Test
    public void testRemoveAnyBoundary() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef("a", "1");
        Map<String, String> expectedMap = this.createFromArgsTest("a", "1");

        /*
         * Call method under test.
         */
        Map.Pair<String, String> pair = map.removeAny();

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(true, expectedMap.hasKey(pair.key()));
        assertEquals(expectedMap.value(pair.key()), pair.value());

        expectedMap.remove(pair.key());
        assertEquals(expectedMap, map);
    }

    /**
     * Test the kernel method removeAny with a routine case.
     */
    @Test
    public void testRemoveAnyRoutine() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef("a", "1", "b", "2",
                "c", "3", "d", "4");
        Map<String, String> expectedMap = this.createFromArgsTest("a", "1", "b",
                "2", "c", "3", "d", "4");

        /*
         * Call method under test.
         */
        Map.Pair<String, String> pair = map.removeAny();

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(true, expectedMap.hasKey(pair.key()));
        assertEquals(expectedMap.value(pair.key()), pair.value());

        expectedMap.remove(pair.key());
        assertEquals(expectedMap, map);
    }

    /**
     * Test the kernel method removeAny with a challenging case.
     */
    @Test
    public void testRemoveAnyChallenging() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef("e", "", "a", "1", "b",
                "2", "c", "3", "d", "4");
        Map<String, String> expectedMap = this.createFromArgsTest("e", "", "a",
                "1", "b", "2", "c", "3", "d", "4");

        /*
         * Call method under test.
         */
        Map.Pair<String, String> pair = map.removeAny();

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(true, expectedMap.hasKey(pair.key()));
        assertEquals(expectedMap.value(pair.key()), pair.value());

        expectedMap.remove(pair.key());
        assertEquals(expectedMap, map);
    }

    /**
     * Tests the kernel method value.
     */

    @Test
    public void testValue_OneElement() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef("a", "1");
        Map<String, String> expectedMap = this.createFromArgsTest("a", "1");

        /*
         * Call method under test.
         */
        String str = map.value("a");

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(expectedMap, map);
        assertEquals("1", str);
    }

    @Test
    public void testValue_ManyElement() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef("a", "1", "b", "2",
                "c", "3");
        Map<String, String> expectedMap = this.createFromArgsTest("a", "1", "b",
                "2", "c", "3");

        /*
         * Call method under test.
         */
        String str = map.value("b");

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(expectedMap, map);
        assertEquals("2", str);
    }

    /**
     * Tests the kernel method hasKey.
     */

    @Test
    public void testHasKey_NoElement() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef();
        Map<String, String> expectedMap = this.createFromArgsTest();

        /*
         * Call method under test.
         */
        boolean results = map.hasKey("b");

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(expectedMap, map);
        assertEquals(false, results);
    }

    @Test
    public void testHasKey_ManyElementsTrue() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef("a", "1", "b", "2",
                "c", "3");
        Map<String, String> expectedMap = this.createFromArgsTest("a", "1", "b",
                "2", "c", "3");

        /*
         * Call method under test.
         */
        boolean results = map.hasKey("b");

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(expectedMap, map);
        assertEquals(true, results);
    }

    @Test
    public void testHasKey_ManyElementsFalse() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef("a", "1", "b", "2",
                "c", "3");
        Map<String, String> expectedMap = this.createFromArgsTest("a", "1", "b",
                "2", "c", "3");

        /*
         * Call method under test.
         */
        boolean results = map.hasKey("f");

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(expectedMap, map);
        assertEquals(false, results);
    }

    /**
     * Tests the kernel method Size.
     */

    @Test
    public void testSize_NoElement() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef();
        Map<String, String> expectedMap = this.createFromArgsTest();

        /*
         * Call method under test.
         */
        int s = map.size();

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(expectedMap, map);
        assertEquals(0, s);
    }

    @Test
    public void testSize_ManyElements() {
        /*
         * Set up the variables.
         */
        Map<String, String> map = this.createFromArgsRef("a", "1", "b", "2",
                "c", "3");
        Map<String, String> expectedMap = this.createFromArgsTest("a", "1", "b",
                "2", "c", "3");

        /*
         * Call method under test.
         */
        int s = map.size();

        /*
         * Assert the values of the variables match expectations.
         */
        assertEquals(expectedMap, map);
        assertEquals(3, s);
    }
}
