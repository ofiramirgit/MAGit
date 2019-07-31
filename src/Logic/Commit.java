package Logic;
import java.util.*;

import static Logic.ConstantsEnums.*;



public class Commit {

        private String m_MainSHA1 = EmptyString;
        private String m_PreviousSHA1 = null;
        private String m_Message = EmptyString;
        private String m_CreatedTime;
        private String m_CreatedBy = EmptyString;

        public void setM_MainSHA1(String m_MainSHA1) {
                this.m_MainSHA1 = m_MainSHA1;
        }

        public void setM_Message(String m_Message) {
                this.m_Message = m_Message;
        }

        public void setM_CreatedTime(String m_CreatedTime) {
                this.m_CreatedTime = m_CreatedTime;
        }

        public void setM_CreatedBy(String m_CreatedBy) {
                this.m_CreatedBy = m_CreatedBy;
        }

        public void setM_PreviousSHA1(String m_PreviousSHA1) {
                this.m_PreviousSHA1 = m_PreviousSHA1;
        }

        @Override
        public String toString() {
                return m_MainSHA1 + ", " + m_PreviousSHA1 + ", " + m_Message + ", " + m_CreatedTime + ", " + m_CreatedBy;
        }
}
