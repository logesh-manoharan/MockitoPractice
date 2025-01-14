Stub - Fake Object

	Where can we use this Stub ?
		If we like to create objects of any dependent classes. [We only create Mock Object for Dependency classes.]
		
		
	// Created Mock Object for CalcService class
	CalcService service = new CalcService(){
		int add (int a, int b) {
			return 0;
		}
	};
	
	// Sample Stub Class to provide Dummy data or Mock data:
	public class BoardGamesStubs implements ExternalService {

		@Override
		public List<String> getBoardGames() {
			return List.of("Chess", "Carom", "Ludo");
		}
	}

	
	
Disadvantages of Stubs:

1.Dynamic Condition - If input parameter/conditions changes. We need to separate IF checks
for every input. It will raise to create Boiler plate code.
2.Service Definition - We need to define every method from the service.

	
To overcome above problems. We are having set of annotations from Mockito which will helpful for us to create Stubs.
	
	
What is Mocking?
	Creating objects that simulates the behavior of real objects.
	

Using Mocks over Stubs:
	It can be dynamically created.
	Offers more functionalities than stubs.
		We can verify method calls and lot of other things


Nice feature of Mocks:
	If we are calling a method in the service/interface which doesn't have any implementation. By default, mock will return
default values. For eg: If return type is 'List', it will be Empty List.

	
Mockito - Library used to create Stub

	Mockito Core - Maven Dependency
	
	
	Creating Stub or Fake Object using Mockito:
		CalcService service = mock(CalcService.class);
		
	
	// Mocking the Add Operation - Below logic wont call/execute the service.add() method. So, while executing add(2, 3), it wont execute actual add() method, instead it make use of 5 returing through thenReturn().	
	when(service.add(2, 3)).thenReturn(5);
	
	// Actual action will be done here
	assertEquals(expected, actual); eg: assertEquals(5, service.add(2, 3));
	
	// Verifying the add method from service is called or not if not test will fail. verify() method from Mockito.
	verify(service.add(2, 3));
	
	
BDD(Behavior-Driven Development) in Mockito:
	How it is differing ? - Code readability will be better.
	Steps followed while writing the test:
		Given - Define the Mocks and Inputs, 
		When  - calling the actual method, 
		Then  - Result Verification
		
		
Argument Capture: [To capture the arguments which was passed while running the specific method.]
	Declare ArgumentCaptor:
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
	
	Define ArgumentCaptor: [While verifying the method call is successful or not]  [This will actually captures and stores the arguments in an Object]
		verify(externalService, never()).deleteBoardGame(stringArgumentCaptor.capture());    // Capturing the argument which was passed
	
	Using that captured object to validate:
		assertEquals(1, stringArgumentCaptor.getValue());
		assertEquals(1, stringArgumentCaptor.getAllValues().size());
	
	

HamcrestMatchers: [it will give us additional matchers]

	hasSize()
	hasItems()
	everyItem(greaterThan(val))
	
Mockito Annotations:

	To make use of all the annotations while running. Class needs a sepcific annotation [@RunWith].
	
		@RunWith(SampleImplTest.class)
		class SampleImplTest{
		
		}

	Legacy:
		SampleImpl sampleImpl = mock(SampleImpl.class);


	With Annotation:  [@Mock]
		@Mock
		SampleImpl sampleImpl;
		
	Legacy:
		@Mock
		SampleService sampleService;
		
		SampleImpl sampleImpl = new SampleImpl(sampleService);

	With Annotation:   [@InjectMocks]
		@InjectMocks   [basically for injecting the mocks into another class which has that Mock class as dependency]
			- It will analyze the SampleImpl class file and finds any dependencies available in the class was matching any Mocks.

		@InjectMocks
		SampleImpl sampleImpl;
	
	@Rule:
		Replacement for Runners [@RunWith(SampleImpl.class)]
		If we use Runner. we can able to use only one at a time. But, with @Rule it is more flexible for us, we can use different
	runners while executing our test.
	
		Eg:
		
		class SampleImplTest {
			
			@Rule
			MockitoRule mockitoRule = MockitoJUnit.rule();    // this will detect all the Mockito Autowire annotations while running
			
			// Likewise If we want to use anyother Runners(Spring runners) we can define it as Rule, 
			// and make use of it in same test.
			
		}
	
	
	@Spy:


		Without @Spy:
		
		class SampleImplTest {
			
			@Test
			public void test_Sample {
				List list = mock(List.class);
				when(list.size()).thenReturn(5);   // whenever size() calls, we are returning 5 by default.
				
				list.add("Dummy Element");         // This won't actually add the element to the list. Wont do actual functionality.
				assertEquals(5, list.size());      // This will pass.
			}
			
		}
		
		With @Spy:
		
		class SampleImplTest {
			
			@Test
			public void test_Sample {
				List list = spy(List.class);
				when(list.size()).thenReturn(5);   	// whenever size() calls, we are returning 5 by default.
				
				list.add("Dummy Element");         	// This will add the element to the list. Allowing us to do 
													// both actual functionality[add()] and stubbing/mocking[size()].
													
				assertEquals(5, list.size());      	// This will fail.
				
				verify(list).add("Dummy Element");	// Also, enables us to verify the method is called or not.
			}
			
		}
		
		
		Lecturer: Recommended to not to use the Spy's. Since we are using both Stubs and Original method it will confusing.
		
		
Why we wont be able to Mock final, static, private Methods ?
	We are doing the Unit test, to make it clean testing the public methods will be perfect and clean. If any method is a 'private'
method it should be called in another 'public' method, we can test that public method.


How can we test the Mock the static, private, constructors ?
	Using PowerMocks we can mock all those.
	It has specific formats to be followed.
	
	
1.It needs separate Runner.
	@RunWith(PowerMockRunner.class)
	
2.Initialize the class which has static method.
	
	@PrepareForTest(StaticClass.class)
	class SampleTest {
	
		@Test
		public void test_sampleMethod () {
			PowerMockito.mockStatic(StaticClass.class);
		}
	}

3.Mock the method:
	when(StaticClass.add(2, 3)).thenReturn(5);
