require "formula"

class @artifactId@ < Formula
  desc 'A command line tool for converting between common multiword token formats'
  homepage 'https://github.com/atl-tw'
  url "@asseturl@"
  sha256 "@assethash@"
  version '@version@'

  # depends_on 'gnu-sed'

  def install
    bin.install 'bin/@artifactId@' => '@artifactId@'
    lib.install 'lib/*'
  end
end