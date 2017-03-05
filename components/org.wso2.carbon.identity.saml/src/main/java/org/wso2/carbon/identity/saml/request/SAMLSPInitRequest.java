/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.saml.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.identity.saml.util.SAMLSSOConstants;

import java.io.UnsupportedEncodingException;

public class SAMLSPInitRequest extends SAMLRequest {

    private static Logger log = LoggerFactory.getLogger(SAMLSPInitRequest.class);

    public SAMLSPInitRequest(SAMLSpInitRequestBuilder builder) {
        super(builder);
    }

    public String getSAMLRequest() {
        if (this.getBodyParameter(SAMLSSOConstants.SAML_REQUEST) != null) {
            return this.getBodyParameter(SAMLSSOConstants.SAML_REQUEST);
        } else {
            try {
                return this.getQueryParameter(SAMLSSOConstants.SAML_REQUEST);
            } catch (UnsupportedEncodingException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Failed to decode the SAML Request ", e);
                }
            }
        }
        return null;
    }

    public String getSignature() {
        if (this.getBodyParameter(SAMLSSOConstants.SIGNATURE) != null) {
            return this.getBodyParameter(SAMLSSOConstants.SIGNATURE);
        } else {
            try {
                return this.getQueryParameter(SAMLSSOConstants.SIGNATURE);
            } catch (UnsupportedEncodingException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Failed to decode the Signature ", e);
                }
            }
        }
        return null;
    }

    public String getSignatureAlgorithm() {
        if (this.getBodyParameter(SAMLSSOConstants.SIG_ALG) != null) {
            return this.getBodyParameter(SAMLSSOConstants.SIG_ALG);
        } else {
            try {
                return this.getQueryParameter(SAMLSSOConstants.SIG_ALG);
            } catch (UnsupportedEncodingException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Failed to decode the Signature Algorithm ", e);
                }
            }
        }
        return null;
    }

    public boolean isRedirect() {
        return this.getHttpMethod() == SAMLSSOConstants.GET_METHOD;
    }

    public static class SAMLSpInitRequestBuilder extends SAMLGatewayRequestBuilder {

        public SAMLSpInitRequestBuilder() {
        }

        @Override
        public SAMLSPInitRequest build() {
            return new SAMLSPInitRequest(this);
        }
    }
}
