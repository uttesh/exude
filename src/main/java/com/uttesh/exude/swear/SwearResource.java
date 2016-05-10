/*
 Copyright 2016-2022 Uttesh Kumar T.H.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.uttesh.exude.swear;

import com.uttesh.exude.common.BaseResource;
import com.uttesh.exude.common.Constants;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Uttesh Kumar T.H.
 */
public class SwearResource implements BaseResource {

    private static Properties properties = null;

    private void loadResource() {
        try {
            if (properties == null) {
                properties = new Properties();
                InputStream input = getClass().getClassLoader().getResourceAsStream(Constants.Resources.SWEAR_EN_FILE);
                if (input != null) {
                    properties.load(input);
                } else {
                    System.err.println("Swear word resource file not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getProperties(String property) {
        if (properties == null) {
            loadResource();
        }
        return properties.getProperty(property);
    }

}
