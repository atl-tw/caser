require "formula"

class @name@ < Formula
  desc '@description@'
  homepage '@homepage@'
  url "@asseturl@"
  sha256 "@assethash@"
  version '@version@'

  depends_on 'openjdk'

  def install
    bin.install 'bin/@artifactId@' => '@artifactId@'
    prefix.install 'lib'
  end
end