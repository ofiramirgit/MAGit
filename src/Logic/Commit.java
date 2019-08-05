package Logic;
import java.util.*;

import static Logic.ConstantsEnums.*;



public class Commit {

        private String m_MainSHA1 = EmptyString;
        private String m_PreviousSHA1 = null;
        private String m_Message = EmptyString;
        private String m_CreatedTime;
        private String m_CreatedBy = EmptyString;
        Commit(){

        }

        Commit(String i_CommitDescription){
                String CommitString[] = i_CommitDescription.split(", ");
                m_MainSHA1 = CommitString[0];
                m_PreviousSHA1 = CommitString[1];
                m_Message = CommitString[2];
                m_CreatedTime = CommitString[3];
                m_CreatedBy = CommitString[4];
        }

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

        public String getM_MainSHA1() {
                return m_MainSHA1;
        }

        public String getM_PreviousSHA1() {
                return m_PreviousSHA1;
        }

        public String getM_Message() {
                return m_Message;
        }

        public String getM_CreatedTime() {
                return m_CreatedTime;
        }

        public String getM_CreatedBy() {
                return m_CreatedBy;
        }

        @Override
        public String toString() {
                return m_MainSHA1 + ", " + m_PreviousSHA1 + ", " + m_Message + ", " + m_CreatedTime + ", " + m_CreatedBy;
        }
}
