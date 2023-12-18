package io.sengokudaikon.isn.api

import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("io/sengokudaikon/isn/api/story")
class CharacterModelStoryTest
