package com.omt.webservice.test.unit;

import com.omt.webservice.Constants;
import com.omt.webservice.UtilLibs;
import com.omt.webservice.entity.UserMessage;
import com.omt.webservice.morningstar.MsSymbolSynThread;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class UtilLibsTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public UtilLibsTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( UtilLibsTest.class );
    }

    /**
     * GetLocalDateFmt
     */
    public void testGetLocalDateFmt(){
    	assertNotNull(UtilLibs.GetLocalDateFmt(null));				//NULL input
    	assertNotNull(UtilLibs.GetLocalDateFmt(" "));				//Space input
    	assertNotNull(UtilLibs.GetLocalDateFmt(""));				//Empty input
    	assertNotNull(UtilLibs.GetLocalDateFmt("abcd"));			//Error input
    	assertNotNull(UtilLibs.GetLocalDateFmt(Constants.SYS_TM_FMT)); 	//Correct input
    }
    
    /**
     * GetCurrentTimeWithTMZ
     */
    public void testGetCurrentTimeWithTMZ(){
		assertNotNull(UtilLibs.GetCurrentTimeWithTMZ(null,"Beijing"));
		assertNotNull(UtilLibs.GetCurrentTimeWithTMZ(null,"Singapore"));
		assertNotNull(UtilLibs.GetCurrentTimeWithTMZ(null,"Australia/Victoria"));
		assertNotNull(UtilLibs.GetCurrentTimeWithTMZ(null,"UTC"));

		assertNotNull(UtilLibs.GetCurrentTimeWithTMZ(null, null));				//NULL input
    	assertNotNull(UtilLibs.GetCurrentTimeWithTMZ(" ", " "));				//Space input
    	assertNotNull(UtilLibs.GetCurrentTimeWithTMZ("", ""));					//Empty input
    	assertNotNull(UtilLibs.GetCurrentTimeWithTMZ("abcd", "123"));			//Error input
    	assertNotNull(UtilLibs.GetCurrentTimeWithTMZ(Constants.SYS_TM_FMT, Constants.SYS_TZ_VIC)); 	//Correct input
    }
    
    /**
     * Check isWeekend
     */
    public void testIsWeekend(){
    	assertFalse(UtilLibs.isWeekend(null));			//Null input
    	assertFalse(UtilLibs.isWeekend(""));			//Empty input
    	assertFalse(UtilLibs.isWeekend(" "));			//Empty input
    	assertFalse(UtilLibs.isWeekend("32/19/2016"));	//Error input
    	assertFalse(UtilLibs.isWeekend("2/19/2016"));	//Error input
    	assertFalse(UtilLibs.isWeekend("2016-09-02"));	//Error date format
    	
    	assertTrue(UtilLibs.isWeekend("03/09/2016"));	//Correct format
    	assertTrue(UtilLibs.isWeekend("04/09/2016"));	//Correct format
    }

    /**
     * Check isWeekend
     */
    public void testIsWeekendFmt(){
    	assertFalse(UtilLibs.isWeekendFmt(null));			//Null input
    	assertFalse(UtilLibs.isWeekendFmt(""));				//Empty input
    	assertFalse(UtilLibs.isWeekendFmt(" "));			//Empty input
    	assertFalse(UtilLibs.isWeekendFmt("2016-00-00"));	//Error input
    	assertFalse(UtilLibs.isWeekendFmt("2016-01-99"));	//Error input
    	assertFalse(UtilLibs.isWeekendFmt("2016-33-02"));	//Error date format
    	
    	assertTrue(UtilLibs.isWeekendFmt("2016-09-03"));	//Correct format
    	assertTrue(UtilLibs.isWeekendFmt("2016-09-04"));	//Correct format
    }
    
    /**
     * Testing on downloading files from Moringstar ftp server
     */
    public void testDownloadFromMs(){
    	assertTrue(MsSymbolSynThread.updateSymbols());
    }
    
    /**
     * Check isHoliday
     */
    public void testIsHoliday(){
    	assertFalse(UtilLibs.isAuHoliday(null));
    	assertFalse(UtilLibs.isAuHoliday(""));
    	assertFalse(UtilLibs.isAuHoliday(" "));
    	assertFalse(UtilLibs.isAuHoliday("2016/01/01"));
    	assertFalse(UtilLibs.isAuHoliday("01-01-2016"));

    	assertTrue(UtilLibs.isAuHoliday("2016-01-01"));
    	assertTrue(UtilLibs.isAuHoliday("2016-01-26"));
    }
    
    /**
     * isNeedRunMsTask test
     */
    public void testIsNeedRunMsTask(){
    	assertFalse(UtilLibs.isNeedRunMsTask("21","77"));
    	assertFalse(UtilLibs.isNeedRunMsTask("12","06"));
		assertFalse(UtilLibs.isNeedRunMsTask("50","24"));
		assertFalse(UtilLibs.isNeedRunMsTask("12","10"));
		assertFalse(UtilLibs.isNeedRunMsTask("aa","02"));
		assertFalse(UtilLibs.isNeedRunMsTask("",null));

		assertTrue(UtilLibs.isNeedRunMsTask("06","18"));
		assertTrue(UtilLibs.isTimeBetweenTwoTime(Constants.USER_TM_START, Constants.USER_TM_END, "09:00:00"));
    }
    /**
     * Get random string
     */
    public void testGetRandomStr(){
    	assertNotNull(UtilLibs.GetRandBatchId());
    	assertNotNull(UtilLibs.GetSysRondomString());
    	assertNotNull(UtilLibs.GetSysRandomNumber());

    	assertNotSame(UtilLibs.GetRandBatchId(), "");
    	assertNotSame(UtilLibs.GetSysRondomString(), "");
    	assertNotSame(UtilLibs.GetSysRandomNumber(), "");
    }
    
    /**
     * 
     */
    public void testGetDayList(){
    	assertNotNull(UtilLibs.getAllDaysList("1W"));
    	assertSame(UtilLibs.getAllDaysList("1W").size(), 7);

    	assertNotNull(UtilLibs.getDaysListExceptHolsWeekend("1W"));
    	assertSame(UtilLibs.getDaysListExceptHolsWeekend("1W").size(), 5);
    }
    
    /**
     * GetCurrentTimeWithTMZ
     */
    public void testGetNextDayDirectly(){
    	assertNotNull(UtilLibs.GetNextDay3Am());				
    	assertNotNull(UtilLibs.GetNextDay4Am());				
    	assertNotNull(UtilLibs.GetNextDay5Am());				
    	assertNotNull(UtilLibs.GetNextDay6Am());				
    	assertNotNull(UtilLibs.GetNextDay7Am());				
    	assertNotNull(UtilLibs.GetNextDay8Am());
    	assertNotNull(UtilLibs.GetNextDay9Am());				
    	assertNotNull(UtilLibs.GetNextDay10Am());
    }
    
    /**
     * GetNextDayTime(1,2,3,4,"")
     */
    public void testGetNextDayTime(){
    	assertNotNull(UtilLibs.GetNextDayTime(0,0,0,0,null));						//NULL input
    	assertNotNull(UtilLibs.GetNextDayTime(0,0,0,0,""));							//Empty input
    	assertNotNull(UtilLibs.GetNextDayTime(0,0,0,0,"abc"));						//Error input
    	assertNotNull(UtilLibs.GetNextDayTime(100,20000,3000,1110,null));			//Error input
    	
    	assertNotNull(UtilLibs.GetNextDayTime(1,2,3,4,Constants.SYS_TZ_VIC));		// Plus
    	assertNotNull(UtilLibs.GetNextDayTime(-10,-2,-3,-4,Constants.SYS_TZ_VIC)); 	// Minus
    }
    
    /**
     * ValidCode Test :-)
     */
    public void testValidCode()
    {
		assertTrue(UtilLibs.validCode("OMT"));   		// Correct
		assertTrue(UtilLibs.validCode("wbcHA"));		// Compliant non case-sensitive
		assertTrue(UtilLibs.validCode("1PG"));			// [0-9][a-z]
		
        assertFalse(UtilLibs.validCode("WB"));			// Exceed length[3-6]
        assertFalse(UtilLibs.validCode("abcdefghi")); 	// Exceed length[3-6]
        assertFalse(UtilLibs.validCode("11@#"));		// [0-9][a-z]
        assertFalse(UtilLibs.validCode(" "));			// Empty
        assertFalse(UtilLibs.validCode(""));			// Empty
        assertFalse(UtilLibs.validCode(null));			// Null
    }

    /**
     * ValidQuestion Test :-)
     */
    public void testValidQuestion()
    {
    	UserMessage message = new UserMessage();
    	//message.setCode("");	// Auto set
    	//message.setStatus(1); // Auto set
    	//message.setId(id);    // Auto set
    	
    	message.setName("name");
    	message.setNotes("notes");
    	message.setVac("vac");
    	message.setDatetime("31/08/2016 06:22:22");
    	message.setEmail("t.liu@omnimarkettide.com");
		assertTrue(UtilLibs.validQuestion(message));		// All is correct
    	message.setDatetime("31/08/2016 6:22:22");
		assertTrue(UtilLibs.validQuestion(message));		// All is correct
		
    	message.setName(null);
    	assertFalse(UtilLibs.validQuestion(message));		// Error date format
        
		message.setDatetime("31-08-2016 06:22:22");
        assertFalse(UtilLibs.validQuestion(message));		// Error date format
		message.setDatetime("1-8-2016 06:22:22");
        assertFalse(UtilLibs.validQuestion(message));		// Error date format
		message.setDatetime("1-8-2016 06:22:77");
        assertFalse(UtilLibs.validQuestion(message));		// Error date format
		message.setDatetime("1-8-2016 06:22:21Z");
        assertFalse(UtilLibs.validQuestion(message));		// Error date format
		message.setDatetime("10/30/2016 06:22:21Z");
        assertFalse(UtilLibs.validQuestion(message));		// Error date format
        
		message.setEmail("t.liuomnimarkettide.com");
        assertFalse(UtilLibs.validQuestion(message));		// Error email format
		message.setEmail("t.liu@omnimarkettide.com..");
        assertFalse(UtilLibs.validQuestion(message));		// Error email format
		message.setEmail("t.l@iu@omnimarkettide.com");
        assertFalse(UtilLibs.validQuestion(message));		// Error email format
		message.setEmail("t.liu@omnimarkettide.com.company");
        assertFalse(UtilLibs.validQuestion(message));		// Error email format
    }
    
    
}
